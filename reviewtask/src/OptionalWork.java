import java.util.Optional;

public class OptionalWork {

    //stream形式红Optional得使用

    public static void main(String[] args) {

        //创建optional实例，也可以通过方法返回值得到

        Optional<String> name=Optional.of("Sanaulla");

        //创建没有值得optional实例，例如值为null

        Optional empty=Optional.ofNullable(null);

        //isPresent method used to make sure if the instance with a value

        if(name.isPresent()){
            //use .get() to get the value of Optional instance

            System.out.println("value of instance is "+name.get());
        }

        //trigger 'NoSuchElementException' while call .get() of Optional instance

        try {
            System.out.println("empty instance trigger .get():"+empty.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        //ifPresent() receive Lambda as params
        //if the instance not empty,Lambda will work with it
        name.ifPresent((value)->{
            System.out.println("the length of the value is "+ value.length());
        });

        //orElse() will return Optional instance otherwise it return error message

        System.out.println(empty.orElse("there is no value present!"));
        System.out.println(name.orElse("some value exists!"));

        //orElseGet() differentiate with orElse() in the parameter passed in
        //orElseGet() receive Lambda and make default value

        System.out.println(empty.orElseGet(()->"Default Value"));
        System.out.println(name.orElseGet(()->"Default Value"));

        //orElseThrow() 与orElse方法类似，区别在于返回值
        //orElseThrow抛出由传入得Lambda表达式生成异常




        //map方法传入得Lambda表达式修改Optional实例默认值
        //Lambda表达式返回值包装为Optional实例

        Optional<String> upperName=name.map((value)->value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        //flapMap与map非常类似，区别在于lambda表达式得返回值
        //map方法lambda表达式返回值可以是任何类型，但是返回值会被包装成Optional实例
        //但是flapMap方法得lambda返回值总是Optional类型

        upperName = name.flatMap((value)->Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));

        //filter方法检查Optional值是否满足给定条件
        //如果满足返回Optional实例值，否则返回空Optional实例

        Optional<String> longName=name.filter((value)->value.length()>6);
        System.out.println(longName.orElse("the name is less than 6 characters"));

        //Optional值不满足给定条件
        Optional<String> anotherName = Optional.of("Sana");
        Optional<String> shortName = anotherName.filter((value)->value.length()>6);

        System.out.println(shortName.orElse("the name is less than 6 characters"));

    }
}
