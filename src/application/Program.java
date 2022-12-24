package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {

                String[] fields = line.split(",");
                int month = Integer.parseInt(fields[0]);
                int year = Integer.parseInt(fields[1]);
                int items = Integer.parseInt(fields[3]);
                double total = Double.parseDouble(fields[4]);

                list.add(new Sale(month, year, fields[2], items, total));

                line = br.readLine();
            }
            System.out.println();
            Set<String> setNames = new LinkedHashSet<>();
            list.forEach(p -> setNames.add(p.getSeller()));
            for (String sale : setNames) {
                Double sum = list.stream().filter(p->p.getSeller().equals(sale))
                        .mapToDouble(p -> p.getTotal()).sum();
                System.out.println(sale + " - R$ " + String.format("%.2f", sum));
            }
        }
        catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        sc.close();
    }
}
