/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Raul
 */
  

public final class PropertyValues {
 
 private InputStream inputStream;

 public final Properties getPropValues() throws IOException {
  Properties prop = null;
  try {
   prop = new Properties();
   String propFileName = "properties/config.properties";

   inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

   if (inputStream != null) {
    prop.load(inputStream);
   } else {
    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
   }
   
  } catch (Exception e) {
   System.out.println("Exception: " + e);
  } finally {
   inputStream.close();
  }
  
  return prop;
 }
}
  
    
