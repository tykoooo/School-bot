package schoolbot.natives;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.awt.Color;
import java.awt.color.*;

import com.iwebpp.crypto.TweetNaclFast.Hash;
import java.util.HashMap;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class School {

    private String schoolName;
    private String emailSuffix;
    private HashMap<String, Classroom> listOfClasses;
    private HashMap<String, Student> listOfStudents;
    
    public School() {
        
    }

    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public School(String schoolname, String emailSuffix, HashMap<String, Classroom> listOfClasses, HashMap<String, Student> listOfStudents) {
        this.schoolName = schoolname;
        this.emailSuffix = emailSuffix;
        this.listOfClasses = listOfClasses;
        this.listOfStudents = listOfStudents;
    }


    /**
     * Getters & Setters
     */

     /**
      * @return list of all classes at the university
      */
     public HashMap<String, Classroom> getListOfClasses() {
         return listOfClasses;
     }
     
     /**
      * 
      * @return school name
      */
     public String getSchoolName() {
         return schoolName;
     }

     /**
      * 
      * @return
      */
     public HashMap<String, Student> getListOfStudents() {
         return listOfStudents;
     }

      /**
       * 
       * @param listOfClasses
       */
      public void setListOfClasses(HashMap<String, Classroom> listOfClasses) {
          this.listOfClasses = listOfClasses;
      }
      
      /**
       * 
       * @param listOfStudents
       */
      public void setListOfStudents(HashMap<String, Student> listOfStudents) {
          this.listOfStudents = listOfStudents;
      }

      /**
       * 
       * @param schoolName
       */
      public void setSchoolName(String schoolName) {
          this.schoolName = schoolName;
      }

      public void addStudent(Student student) {
          listOfStudents.putIfAbsent(student.getRealName(), student);
      }

      public boolean removeStudent(Student student) {
          if (!listOfStudents.containsKey(student.getRealName())) return false;
          else listOfClasses.remove(student.getRealName()); return true;

      } 


      public EmbedBuilder getAsEmbed() {
        Date dateGenerated = new Date();
        EmbedBuilder pretyifyEmbed = new EmbedBuilder();
        pretyifyEmbed.setTitle(":books: " + captializer(schoolName) + " :books:");
        pretyifyEmbed.setColor(Color.blue);
        pretyifyEmbed.setTitle(":books: University Information :books:");
        pretyifyEmbed.setDescription("School name: " + schoolName + "\n" + 
                                     "Number of students: " + listOfClasses.size() + "\n" + 
                                     "Number of Classes: " + listOfStudents.size());
        pretyifyEmbed.setFooter("Generated on: " +  dateGenerated.getMonth() + "/" + dateGenerated.getDay() + "/" + dateGenerated.getYear());
        return pretyifyEmbed;
          
      }

      private String captializer(String str) {
            //if string is null or empty, return empty string
            if(str == null || str.length() == 0)
                return "";
            
            /* 
             * if string contains only one char,
             * make it capital and return 
             */
            if(str.length() == 1)
                return str.toUpperCase();
            
            
            /*
             * Split the string by space
             */
            String[] words = str.split(" ");
            
            //create empty StringBuilder with same length as string
            StringBuilder sbCapitalizedWords = new StringBuilder(str.length());
            
            /*
             * For each word, 
             *     1. get first character using substring
             *     2. Make it upper case and append to string builder
             *     3. append the rest of the characters as-is to string builder
             *     4. append space to string builder
             */
            for(String word : words){            
                
                if(word.length() > 1)
                    sbCapitalizedWords
                        .append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1));
                else
                    sbCapitalizedWords.append(word.toUpperCase());
                
                sbCapitalizedWords.append(" ");
            }
            
            /*
             * convert StringBuilder to string, also
             * remove last space from it using trim method
             */
            return sbCapitalizedWords.toString().trim();
        }
      

        @Override
        public String toString() {
            return "School [emailSuffix=" + emailSuffix + ", listOfClasses=" + listOfClasses + ", listOfStudents="
                    + listOfStudents + ", schoolName=" + schoolName + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((emailSuffix == null) ? 0 : emailSuffix.hashCode());
            result = prime * result + ((listOfClasses == null) ? 0 : listOfClasses.hashCode());
            result = prime * result + ((listOfStudents == null) ? 0 : listOfStudents.hashCode());
            result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            School other = (School) obj;
            if (emailSuffix == null) {
                if (other.emailSuffix != null)
                    return false;
            } else if (!emailSuffix.equals(other.emailSuffix))
                return false;
            if (listOfClasses == null) {
                if (other.listOfClasses != null)
                    return false;
            } else if (!listOfClasses.equals(other.listOfClasses))
                return false;
            if (listOfStudents == null) {
                if (other.listOfStudents != null)
                    return false;
            } else if (!listOfStudents.equals(other.listOfStudents))
                return false;
            if (schoolName == null) {
                if (other.schoolName != null)
                    return false;
            } else if (!schoolName.equals(other.schoolName))
                return false;
            return true;
        }
}