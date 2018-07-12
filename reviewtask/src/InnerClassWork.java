public class InnerClassWork {

    //
    /**static*/ class Inner{}

    public static void foo(){
        new InnerClassWork().new Inner();
    }

    public void bar(){
        new Inner();
    }

    public static void main(String[] args) {
        new InnerClassWork().new Inner();
    }
}
