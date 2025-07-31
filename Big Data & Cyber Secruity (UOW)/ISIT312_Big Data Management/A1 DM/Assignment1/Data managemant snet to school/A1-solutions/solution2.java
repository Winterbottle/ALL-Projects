//Name: Jeslyn ho
//SIM ID:10241485
//Assingment 1 Task 2

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class solution2 {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "speed limit checker");
        job.setJarByClass(solution2.class);
        job.setMapperClass(SMapper.class);
        job.setReducerClass(SReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class SMapper extends Mapper<Object, Text, Text, DoubleWritable> {
        private Text vehicleDetails = new Text();
        private DoubleWritable speed = new DoubleWritable();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            // Split the line into tokens
            StringTokenizer itr = new StringTokenizer(value.toString());
            if (itr.countTokens() == 4) { // Ensure there are four tokens
                String registration = itr.nextToken(); // Car registration
                String location = itr.nextToken(); // Camera location
                String speedStr = itr.nextToken(); // Speed (not used directly here)
                double carSpeed = Double.parseDouble(itr.nextToken()); // Average speed

                // Send out the position, speed, and registration
                vehicleDetails.set(registration + " " + location);
                speed.set(carSpeed);
                context.write(vehicleDetails, speed);
            }
        }
    }

    public static class SReducer extends Reducer<Text, DoubleWritable, Text, String> {
        private String result;

        public void reduce(Text key, Iterable<DoubleWritable> speeds, Context context) throws IOException, InterruptedException {
            double sum = 0;
            int count = 0;

            for (DoubleWritable val : speeds) {
                sum += val.get();
                count++;
            }

            if (count > 0) { // Determine the average speed.
                double averageSpeed = sum / count;

                if (averageSpeed > 90) { // Only log speeds that are higher than the limit.
                    result = "Speed limit exceeded!! Average Speed: " + String.format("%.2f", averageSpeed);
                    context.write(key, result);
                }
            }
        }
    }
}
