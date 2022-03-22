class Calculate {
    static int square(int x){
        return x*x;
    }
    static int cube(int x){
        return x*square(x);
    }
    static double average(double a, double b){
        return (a+b)/2;
    }
    static double average(double a, double b, double c){
        return (a+b+c)/3;
    }
    static double toDegrees(double x){
        return x*180/3.14159;
    }
    static double toRadians(double x){
        return x*3.14159/180;
    }
    static double discriminant(double a, double b, double c){
        return b*b - 4*a*c;
    }
    static String toImproperFrac(int a, int b, int c){
        return (a*c)+b+"/"+c;
    }
    static String toMixedNum(int a, int b){
        int c= a/b;
        return c+"_"+(a-c*b)+"/"+b;
    }
    static String foil(int a, int b, int c, int d, String s){
        int ax = a*c;
        int bx = a*d + b*c;
        int cx = b*d;
        return String.format("%dn^2 + %dn + %d", ax, bx, cx);
    }
    static boolean isDivisibleBy(int x, int y) throws IllegalArgumentException{
        if(y==0) throw new IllegalArgumentException("Dividing by 0 is illegal");
        return x%y==0;
    }
    static double absValue(double x){
        if(x>=0) return x;
        return -x;
    }
    static double max(double x, double y){
        return  (x>y?x:y);
    }
    static double max(double x, double y, double z){
        double temp = (x>y?x:y);
        return (temp>z?temp:z);
    }
    static int min(int x, int y){
        return (x>y?y:x);
    }
    static double min(double x, double y){
        return (x>y?y:x);
    }
    static double round2(double x){
        boolean isNeg = (x<0);
        x = absValue(x);
        int temp = (int)(x*1000);
        if(temp%10>=5) {
            temp-=temp%10;
            temp+=10;
            if(isNeg) return -1 * (temp/1000.0);
            return ((temp)/1000.0);
        }
        temp = (int)(x*100);
        if(isNeg) return -temp/100.0;
        return temp/100.0;
    }
    static double exponent(double base, int pow) throws IllegalArgumentException{
        if(pow<0) throw new IllegalArgumentException("Exponent input must be >=0");
        double a=base;
        double ans =1;
        while(pow>0){
            if((pow&1)==1) ans*=a;
            a*=a;
            pow>>=1;
        }
        return ans;
    }
    static int factorial(int x)  throws IllegalArgumentException{
        if(x<0) throw new IllegalArgumentException("Factorial input must be >=0");
        int ans=1;
        for(int i=2; i<=x; i++){
            ans*=i;
        }
        return ans;
    }
    static boolean isPrime(int x){
        for(int i=2; i<=sqrt(x); i++){
            if(isDivisibleBy(x,i)) return false;
        }
        return true;
    }
    static int gcf(int a,int b){
        if(a==0) return b;
        if(b==0) return a;
        for(int i=min(a,b); i>=1; i--){
            if(isDivisibleBy(a,i) && isDivisibleBy(b,i)) return i;
        }
        return 1;
    }
    static double sqrt(double x) throws IllegalArgumentException{
        if(x<0) throw new IllegalArgumentException("Square Root input must be >=0");
        double ans = 1;
        while(absValue(ans*ans - x) >.005) ans = 0.5*(x/ans + ans);
        return round2(ans);
    }
    static String quadForm(int a, int b, int c){
        int disc =  (int) discriminant(a,b,c);
        if(disc < 0) return "no real roots";
        if(disc==0){
            return "" + (-1.0* b/(2*a));
        }
        double mx = ((-1.0* b + sqrt(disc))/(2*a));
        double nx = ((-1.0* b - sqrt(disc))/(2*a));
        double small = min(mx, nx);
        double big = max(mx, nx);
        return ""+small+" and "+big;
    }
}

