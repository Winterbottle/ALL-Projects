//Name: Jeslyn ho
//SIM ID:10241485
//Assingment 1 Task 1

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;

public class solution1 {
  public static void main(String[] args) throws Exception {
    if (args.length < 3) {
     System.exit(1);
    }
    // Obtaining the input and output file paths' parameters
    String file1 = args[0];
    String file2 = args[1];
    String file3 = args[2];

    // Configuring the FileSystem and Hadoop Configuration
    Configuration conf = new Configuration();
    FileSystem hdfs = FileSystem.get(URI.create(file3), conf);

    // Specify the HDFS paths for the input and output files.
    Path inputfile1 = new Path(file1);
    Path inputfile2 = new Path(file2);
    Path outputfile3 = new Path(file3);

    // Declaring input and output streams
    FSDataInputStream f1 = null;
    FSDataInputStream f2 = null;
    FSDataOutputStream f3 = null;

  try {
    // Open the first input file
    f1 = hdfs.open(inputfile1);
    // Open the second input file
    f2 = hdfs.open(inputfile2);
    // Create the output file
    f3 = hdfs.create(outputfile3);

    // Merging the contents of the first file into the output file
    IOUtils.copyBytes(f1, f3, 4096, false);  // false to keep output stream open

    // Merging the contents of the second file into the same output file
    IOUtils.copyBytes(f2, f3, 4096, true);  // true closes the output stream after copying

  } finally {
    // Closing all streams
    IOUtils.closeStream(f1);
    IOUtils.closeStream(f2);
    }
  }
}