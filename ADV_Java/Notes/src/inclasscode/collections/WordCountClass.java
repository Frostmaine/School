
package inclasscode.collections;

import java.util.Objects;

/**
 *
 * @author Compsci 221
 * @version Fall 2021
 * 
 */
public class WordCountClass {
    public String word;
    public int count; 

    public WordCountClass(String word, int count) {
        this.word = word;
        this.count = count;
    }

   
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public String toString(){
        
        return word+"\t" + count; 
    }
    
        @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.word);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WordCountClass other = (WordCountClass) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        return true;
    }
}
