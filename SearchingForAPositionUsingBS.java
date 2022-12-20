import java.util.Scanner;

/*
 * 5. Program dosazuje číslo do vzestupně setříděné posloupnosti,
 * za podmínek, že se dané číslo v posloupnosti nevyskytuje.
 * @autor David Mrlík
 * @ 8.12.2022 
 */

public class SearchingForAPositionUsingBS {
     
    public static final Scanner sc = new Scanner(System.in);

    public static int[] getSequence(){              //tato funkce načítá vstupní posloupnost a její délku
        System.out.println("Zadej počet prvků počáteční posloupnosti: ");
        int count = sc.nextInt();
        if(count < 0) count = 0;                        //abychom předešli konzolovému erroru a pádu programu
        int[] arr = new int[count];
        if(count != 0)System.out.println("Zadej počáteční vzestupně setříděnou posloupnost: ");
        for(int i = 0; i < count; i++){
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    public static int getArrLength(int[] arr){
        return arr.length;
    }

    public static boolean isUnique(int[] arr){              
        boolean unique = true;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if( i != j){
                    if(arr[i] == arr[j]) unique = false;
                }
            }
        }
        return unique;
    }

    public static boolean isSorted(int[] arr){
        boolean sorted = true;
        for(int i = 1; i < arr.length; i++){
            if(arr[i-1] > arr[i]) sorted = false;
        }
        return sorted;
    }

    public static boolean isPositive(int[] arr){
        boolean positive = true;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < 0) positive = false;
        }
        return positive;
    }

    public static int[] getElementAndClassifyIt(int[] arr){
        int x = 0;
        while(x >= 0){  
            System.out.println("Zadávej další hodnoty: ");                     
            x = sc.nextInt();
            if(x < 0) break;
            arr = newArray(arr, x);   //načtu prvek a zakládám mu nové pole 
        }
        return arr; 
    }

    public static int[] newArray(int[] arr, int x){ 
        int[] newArr = new int[arr.length+1];
        int left = 0, right= arr.length-1;      //hledam index prvku v tom nezvetsenem poli 
        for(int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        newArr = binarySearch(newArr, x, left, right);  
        return newArr; 
    }

    public static int[] binarySearch(int[] newArr,int x,int left,int right){  //hledám místo pro prvek v setříděné posloupnosti
        int mid, position = 0;                                      
        boolean occurs = false;

        if((left+right)%2 == 0) mid = (left+right)/2;               
        else mid = (left+right+1)/2;
        if(newArr[right] < x){                        //nejprve zjišťuji zda-li se nachází na poslední nebo první pozici
            position = newArr.length-1;                   
        } else if(newArr[left] > x){
            position = 0;
        } else {
            while(left <= right){                       //pak aplikuji binary search, dělím pole na půlky, hledám buď v levé pokud je prvek menší než střed nebo v pravé pokud je tomu naopak
                if(newArr[mid] == x){                     
                    occurs = true;
                    break;
                }
                else if(newArr[mid] < x) left = mid + 1;
                else if(newArr[mid] > x) right = mid - 1;
                if(left >= right){                                      //tato podmínka je zde, proto aby se neporušila podmínka while cyklu a tím by prvku nebyla přidělena žádná pozice (za podmínky, že se v posloupnosti prvek nevyskytuje)   
                    if(newArr[left] > x) position = left;                   //ukládám do pozice pouze levou stranu, protože metodou výpočtu středu, se dostanu do situace, kdy je střed rovný pravé straně (kvůli té + 1)      
                    else if (newArr[left] == x || newArr[right] == x) occurs = true;       //pokud je při této situaci prvek menší než střed, musím do pozice uložit levou stranu. 
                    else position = left + 1;
                    break;
                }
                if((left+right)%2 == 0) mid = (left+right)/2;
                else mid = (left+right+1)/2;
            }    
        }
        if(occurs){                                                                     //pokud se tam zadaný prvek nachází, vracím předchozí nezvětšené pole            
            System.out.println("Zadaný prvek se v posloupnosti již vyskytuje !");
            int[] arr = new int[newArr.length-1];
            for(int i = 0; i < arr.length; i++){
                arr[i] = newArr[i];                                         
            }
            return arr;
        } else{
            for(int i = newArr.length-1; i > position; i--){
                newArr[i] = newArr[i-1];
            }
            newArr[position] = x;
            return newArr;
        }
    }

    public static void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static int menu(){
        System.out.print("Pokračovat ve zpracování (a/n):");
        String newTask = sc.next();
        int res = newTask.equals("a") ? 1 : 0;
        return res; 
    }

    public static void startProgram(){
        int newTask = 1, arrLength; 
        boolean sorted, positive, unique;
        while(newTask == 1){
            int[] arr = getSequence();
            arrLength = getArrLength(arr);
            if(arrLength == 0){
                System.out.println("Zadaná posloupnost nemůže být délky 0 či záporná !");
                continue;
            }
            unique = isUnique(arr);
            if(!unique){
                System.out.println("Zadaná posloupnost obsahuje stejné prvky !");
                continue;
            }
            positive = isPositive(arr);
            if(!positive){
                System.out.println("Zadaná posloupnost obsahuje záporná čísla !");
                continue;
            }             
            sorted = isSorted(arr);
            if(!sorted){
                System.out.println("Zadaná posloupnost není setříděná !");
                continue;
            }      
            arr = getElementAndClassifyIt(arr);
            printArr(arr);
            newTask = menu();
        }
    }

    public static void main(String[] args){
        startProgram();
        
        // int[] arr = {1, 5, 7, 9};
        // int x = 3;
        // int[] newArr = newArray(arr, x);
        // printArr(newArr);

        // int i = 0;
        // int n = 50;
        // int[] arr = Test.getRandomArr(n);
        // arr = Test.sort(arr);
        // System.out.println("Zadaná posloupnost: ");
        // printArr(arr); 
        // while (i < 10){
        //     int x = Test.getRandomElement();
        //     System.out.println();
        //     System.out.println("Prvek k setřídění: "+x);
        //     System.out.println();
        //     System.out.println("Pole po zadání prvku k zatřídění: ");
        //     arr = newArray(arr, x);
        //     printArr(arr);
        //     i++;
        // }
    }
}
