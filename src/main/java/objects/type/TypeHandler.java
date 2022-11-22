package objects.type;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TypeHandler {

    List<Type> typeList;

    public TypeHandler() {
        typeList = Arrays.asList(Type.values());
    }

    public Type selectType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a type:");
        for (int i = 0; i < typeList.size(); i++) {
            if (i % 7 == 6) {
                System.out.println();
            }
            System.out.print(i + "-" + typeList.get(i) + "\t");
        }
        System.out.print("\nYour selection: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Confirm type: " + typeList.get(option));
        return typeList.get(option);
    }
}
