static String lastFirst(String s){
    String[] arr = s.split(" ");
    return arr[1]+", " + arr[0].charAt(0)+".";
}