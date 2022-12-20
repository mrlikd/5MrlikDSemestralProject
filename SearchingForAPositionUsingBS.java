import java.util.Scanner;

/**
 * 5. Program dosazuje číslo do vzestupně setříděné posloupnosti,
 * za podmínek, že se dané číslo v posloupnosti nevyskytuje.
 * @autor David Mrlík
 * @ 8.12.2022 
 **/


public class SearchingForAPositionUsingBS {
     
    public static final Scanner sc = new Scanner(System.in);

    /**
     * 
     * @return array of certain length
     */
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

    /**
     * 
     * 
     * @param arr
     * @return length of an array;
     */
    public static int getArrLength(int[] arr){
        return arr.length;
    }

    /**
     * 
     * @param arr
     * @return true if arr is Unique. False if its not
     */
    public static boolean isUnique(int[] arr){              
        for(int i = 0; i < arr.length-1; i++){
            for(int j = i+1; j < arr.length; j++){
                    if(arr[i] == arr[j]) return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @param arr
     * @return true if an array is sorted. False if its not
     */

    public static boolean isSorted(int[] arr){
        for(int i = 1; i < arr.length; i++){
            if(arr[i-1] > arr[i]) return false;
        }
        return true;
    }

    /**
     * 
     * @param arr
     * @return true if all elements are positive integers. False if are not
     */

    public static boolean isPositive(int[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < 0) return false;
        }
        return true;
    }

    /**
     * Scans for a element from stdin 
     * calls a function binarySearch and newArray
     * 
     * 
     * @param arr
     * @return an array with classified element 
     */
    public static int[] getElementAndClassifyIt(int[] arr){
        int x = 0, position, left = 0, right = arr.length-1;
        while(x >= 0){  
            System.out.println("Zadávej další hodnoty: ");                     
            x = sc.nextInt();
            if(x < 0) break;
            position = binarySearch(arr, x, left, right);
            if(position == -1) System.out.println("Zadaný prvek se v posloupnosti již vyskytuje");
            else arr = newArray(arr, x, position);   //načtu prvek a zakládám mu nové pole 
        }
        return arr; 
    }

    /**
     * creates an array of lenght arr.length+1
     *
     * 
     * @param arr
     * @param x
     * @param position
     * @return new array with element x classified in it.
     */
    public static int[] newArray(int[] arr, int x, int position){ 
        int[] newArr = new int[arr.length+1];
        for(int i = newArr.length-1; i > position; i--){
            newArr[i] = arr[i-1];
        }
        newArr[position] = x;
        for(int i = 0; i < position; i++){
            newArr[i] = arr[i]; 
        } 
        return newArr; 
    }

    /**
     * finds a position for the given element x in given array arr
     * @param arr
     * @param x
     * @param left
     * @param right
     * @return position of the element in array 
     */
    public static int binarySearch(int[] arr,int x,int left,int right){  //hledám místo pro prvek v setříděné posloupnosti
        int mid = (left+right)/2;

        if(arr[right] < x) return arr.length -1;                        //nejprve zjišťuji zda-li se nachází na poslední nebo první poziciarr.length-1;                   
        if(arr[left] > x) return 0;
        
        while(left <= right){                       //pak aplikuji binary search, dělím pole na půlky, hledám buď v levé pokud je prvek menší než střed nebo v pravé pokud je tomu naopak
            if(arr[mid] == x) return -1;
            if(arr[mid] < x) left = mid + 1;
            else if(arr[mid] > x) right = mid - 1;
            mid = (left+right)/2; 
        }    
        if(arr[mid] > x) return right;
        return left;
    }
    /**
     * this function prints an array
     * @param arr
     */
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
