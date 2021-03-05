package operativeDate;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperatvieDataConstantsTest {

    @Test
    public void YesShouldBeGetAttentionProductToBusiness() {
        String expectedResult= "yes";
        Assert.assertTrue(expectedResult.equals(OperatvieDataConstants.getAttentionProductToBusiness("preces birojam")));
    }

    @Test
    public void NoShouldBeGetAttentionProductToBusiness() {
        String expectedResult= "no";
        Assert.assertTrue(expectedResult.equals(OperatvieDataConstants.getAttentionProductToBusiness
                ("ar uzņēmējdarbību nesaistīti izdevumi")));
    }

    @Test
    public void PartlyShouldBeGetAttentionProductToBusiness() {
        String expectedResult= "partly";
        Assert.assertTrue(expectedResult.equals(OperatvieDataConstants.getAttentionProductToBusiness
                ("degviela")));
    }
}