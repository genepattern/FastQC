package org.genepattern.modules;

import java.io.File;

/**
 * Created by nazaire on 3/13/14.
 */
public class FastQCWrapper
{
    public static void main(String[] args) throws Exception
    {
        String unzip = System.getProperty("fastqc.unzip");

        if(unzip != null && (!unzip.equals("true") && !unzip.equals("false")))
        {
            System.err.println("Error: The fastqc.unzip property is " + unzip + ". It must be set to either true or false.");
            System.exit(1);
        }

        String quiet = System.getProperty("fastqc.quiet");

        if(quiet != null && (!quiet.equals("true") && !quiet.equals("false")))
        {
            System.err.println("Error: The fastqc.quiet property is " + quiet + ". It must be set to either true or false.");
            System.exit(1);
        }

        String kmerSize = System.getProperty("fastqc.kmer_size");
        if(kmerSize != null)
        {
            try
            {
                int kmer = Integer.parseInt(kmerSize);
                if(kmer < 2 || kmer > 10)
                {
                    System.err.println("The kmer size " + kmerSize + " is not a number in the range 2-10.");
                    System.exit(1);
                }
            }
            catch(NumberFormatException e)
            {
                System.err.println("The kmer size " + kmerSize + " is not a number in the range 2-10.");
                System.exit(1);
            }
        }

        String outputDir = System.getProperty("fastqc.output_dir");
        if(outputDir != null && outputDir.length() > 0 &&!((new File(outputDir)).exists()))
        {
            System.err.println("Error: The fastqc.output_dir property " + outputDir + " does not exist.");
            System.exit(1);
        }

        String contaminantFile = System.getProperty("fastqc.contaminant_file");
        if(contaminantFile != null && contaminantFile.length() > 0 &&!((new File(contaminantFile)).exists()))
        {
            System.err.println("Error: The contaminantFile property " + contaminantFile + " does not exist.");
            System.exit(1);
        }

        uk.ac.babraham.FastQC.FastQCApplication.main(args);
    }
}
