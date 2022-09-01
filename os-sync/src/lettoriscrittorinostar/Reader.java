package lettoriscrittorinostar;

/**
 * Reader.java
 *
 * A reader to the database.
 *
 * Figure 7.17
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Sixth Edition
 * Copyright John Wiley & Sons - 2003.
 */

public class Reader implements Runnable
{

   private RWLock db;
   private int       readerNum;

   public Reader(int readerNum, RWLock db) {
      this.readerNum = readerNum;
      this.db = db;
   }

   public void run() {
     while (true) {
       SleepUtilities.nap();

       System.out.println("reader " + readerNum + " wants to read.");
       db.acquireReadLock(readerNum);

       // you have access to read from the database
       // let's read for awhile .....
       SleepUtilities.nap();

       db.releaseReadLock(readerNum);
      }
   }
;
}
