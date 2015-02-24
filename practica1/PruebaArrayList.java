import java.util.ArrayList;
public class PruebaArrayList {
 public static void main(String[] args) {
  ArrayList<String> frutas = new ArrayList<String>();
  frutas.add("manzana");
  frutas.remove("manzana");
  frutas.add("naranja");
  frutas.add("pera");

  for (int i=0; i < frutas.size(); i++) {
    String sFrutas = frutas.get(i);
    System.out.println(sFrutas);
  }
 }
}