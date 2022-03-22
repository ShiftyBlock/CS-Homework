Scanner scanner = new Scanner(System.in);
System.out.print("How many numbers? ");
int tcs = scanner.nextInt();
long product = 1;
while(tcs-->0){
    System.out.print("Next number --> ");
    int x= scanner.nextInt();
    product *=x;
}
System.out.println("Product = " + product);