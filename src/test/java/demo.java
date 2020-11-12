import com.cdecube.common.Page;
import com.cdecube.common.Preset;
import org.testng.annotations.Test;

/**
 * @Author: liupeng
 * @Description:
 * @Date: Created in 15:03 2019/6/27
 * @Modified By:
 */
public class demo {
    Preset preset = new Preset("chrome");
    Page page = new Page(preset.getDriver());

@Test
    public void f() {
        page.getUrl("baidu_news");
    }
}