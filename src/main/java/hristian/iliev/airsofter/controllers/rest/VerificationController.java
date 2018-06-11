package hristian.iliev.airsofter.controllers.rest;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.verify.CheckResult;
import com.nexmo.client.verify.VerifyResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class VerificationController {
  private VerifyResult request = null;

  @RequestMapping(value = "/sendVerificationSMS", method = RequestMethod.GET)
  public void sendVerificationSMS(@RequestParam("telephone") String telephone) throws IOException, NexmoClientException {
    System.out.println(telephone);
    AuthMethod auth = new TokenAuthMethod("33048940", "LQBuODK2rXh2LWzW");
    NexmoClient client = new NexmoClient(auth);
    this.request = client.getVerifyClient().verify(telephone, "Airsofter");
  }

  @RequestMapping(value = "/checkVerificationCode", method = RequestMethod.GET)
  public boolean checkVerificationCode(@RequestParam("code") String code) throws IOException, NexmoClientException {
    AuthMethod auth = new TokenAuthMethod("33048940", "LQBuODK2rXh2LWzW");
    NexmoClient client = new NexmoClient(auth);

    CheckResult result = client.getVerifyClient().check(request.getRequestId(), code);

    return true;
  }
}
