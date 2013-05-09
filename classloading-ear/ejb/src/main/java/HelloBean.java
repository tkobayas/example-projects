import javax.ejb.Stateless;

@Stateless
public class HelloBean implements HelloLocal
{
    @Override
    public String sayHello() {
        
        Pojo pojo = new Pojo();
        pojo.version();
        
        return "Hello";
    }
}
