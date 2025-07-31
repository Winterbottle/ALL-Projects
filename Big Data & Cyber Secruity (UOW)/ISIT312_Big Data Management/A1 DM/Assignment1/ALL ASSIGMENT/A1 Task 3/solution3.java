import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class solution3 {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "MinMaxAvgSum");

        // Set the jar file containing this class
        job.setJarByClass(solution3.class);

        // Assign the classes for Mapper and Reducer.
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReducer.class);

        // Configure the keys and values' output types.
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Specify the job's input and output pathways.
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Close the application whether the task is completed successfully.
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable outputValue = new IntWritable(); // Output value (IntWritable)
        private Text itemKey = new Text(); // Output key (Text)

        // A map function that examines every input line
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            // Tokenize the input line
            StringTokenizer tokens = new StringTokenizer(value.toString());

            // As you go through the tokens, release key-value pairs.
            while (tokens.hasMoreTokens()) {
                itemKey.set(tokens.nextToken()); // Set the key (itemKey/item)
                outputValue.set(Integer.parseInt(tokens.nextToken())); // Set the value (integer)
                context.write(itemKey, outputValue); // Emit key-value pair
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, Text> {

        // Reduce function to calculate the result for each key
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {

            // Initialize variables for min, max, sum, and count
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int sum = 0;
            int count = 0;

            // Iterate through the list of values and compute min, max, sum, and count
            for (IntWritable val : values) {
                int currentNum = val.get(); // Get current value

                if (currentNum < min) { // Update min and max
                    min = currentNum;
                }
                if (currentNum > max) {
                    max = currentNum;
                }

                sum += currentNum;
                count++;
            }

            double avg = (double) sum / count;  // Calculate average

            // Construct a result string using the following format: Max, Min, Avg, Sum.
            String result = String.format("Max:%d Min:%d Avg:%.2f Total:%d", max, min, avg, sum);

            // Output the result string and the key.
            context.write(key, new Text(result));
        }
    }
}