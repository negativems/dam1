package ga.mmbh.cfgs;

import java.util.Iterator;

import ga.mmbh.cfgs.exceptions.ColaExceededSizeException;
import ga.mmbh.cfgs.exceptions.ElementBlockedException;
import ga.mmbh.cfgs.exceptions.LlevateTuNullDeAquiException;
import ga.mmbh.cfgs.modules.ColaDelCastigo;

public class MainApp {

   public static void main(String[] args) {
      ColaDelCastigo<Integer> lista = new ColaDelCastigo<Integer>((o1, o2) -> {
         return o1.compareTo(o2);
      });

      // Throws an exception if the value is null or there is no elements on the queue
      try {
         lista.add(7);
         lista.add(25);
         lista.add(1);
         lista.add(9);
         lista.add(13);
      } catch (ColaExceededSizeException | LlevateTuNullDeAquiException e) {
         e.printStackTrace();
      }

      Iterator<Integer> iterator = lista.iterator();
      while (iterator.hasNext()) {
         System.out.println(iterator.next());
      }

      // Tests
      System.out.println("**** TESTS ****");
      testEmptyQueue();
      testNullValue();
      System.out.println("**** END ****");
   }

   private static void testEmptyQueue() {
      ColaDelCastigo<Integer> lista = new ColaDelCastigo<Integer>();

      try {
         lista.add(5);
         lista.remove(5);
      } catch (LlevateTuNullDeAquiException | ColaExceededSizeException | ElementBlockedException e) {
         e.printStackTrace();
      }
   }

   private static void testNullValue() {
      ColaDelCastigo<Integer> lista = new ColaDelCastigo<Integer>();

      try {
         lista.add(null);
      } catch (LlevateTuNullDeAquiException | ColaExceededSizeException e) {
         e.printStackTrace();
      }
   }
}
