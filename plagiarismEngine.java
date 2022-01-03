/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class plagiarismEngine {
    public plagiarismEngine(){
        System.out.println("Initialized\n\n");
    }
     public boolean Start(File Newfile){
  Path currentRelativePath = Paths.get("src/files");
  String cpath = currentRelativePath.toAbsolutePath().toString();
      File folder=new File(cpath);
      File[] files= folder.listFiles();
        HashMap<String, HashMap> hm = new HashMap<>();
        for (File file : files)
        {
            String fileType = file.getName().substring(file.getName().indexOf('.'));
            if(fileType.equals(".txt")){
                ArrayList<String> a = new ArrayList<>();
                int wordCount = 0;

                try{
                    String line;
                    InputStream fis = new FileInputStream(file.getAbsolutePath());
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(isr);
                    while ((line = (br.readLine())) != null) {
                        line=line.trim();
                        String[] words = line.split(" ");
                        for(String s: words){
                                String ns = " ";
                                if(!s.equals(ns) && !s.equals("")){
                                    wordCount += 1;
                                    a.add(s);

                            }
                        }
                    }

                    HashMap<String, Integer> vhm = new HashMap<>();
                    for(int i=0; i<a.size(); i++){
                        String vs = a.get(i);
                            //vhm.put(vs, vhm.get(vs));
                        
                    }
                    vhm.put("wordCount", wordCount);

                    hm.put(file.getName(), vhm);

                }
                catch(IOException e){
                    System.out.println("Not Found");
                }



            }

        }
        for(int i=0; i<files.length-1; i++){
            String fileType1 = files[i].getName().substring(files[i].getName().indexOf('.'));

            if(fileType1.equals(".txt")){
                String filename1 = files[i].getName();

                HashMap<String, Integer> samplefile1 = hm.get(filename1);
                int totalWords = samplefile1.get("wordCount");
                ArrayList<String> l1 = new ArrayList<String>(samplefile1.keySet());
                int st = l1.size();

                for(int j=i+1; j<files.length; j++){

                    String fileType2 = files[j].getName().substring(files[j].getName().indexOf('.'));
                    if(fileType2.equals(".txt")){
                        int copy = 0;
                        String filename2 = files[j].getName();
                        HashMap<String, Integer> samplefile2 = hm.get(filename2);
                       ArrayList<String> l2 = new ArrayList<String>(samplefile2.keySet());
                        int x = 0;
                        while(x<st-1){
                            System.out.println(filename1 + filename2 + " ");
                            String cmp = l1.get(x);
                            x += 1;
                            if(samplefile2.containsKey(cmp) && cmp!="wordCount"){
//                                System.out.println(cmp);
                                copy += samplefile2.get(cmp);
                            }

                        }
                        float perMatch = (float)copy/totalWords;
                        int match = (int)(perMatch*100);

                        System.out.println(filename1 + " and " + filename2 + " has " + match);

                    }
                }


            }

        }
return true;
    }  
 }

