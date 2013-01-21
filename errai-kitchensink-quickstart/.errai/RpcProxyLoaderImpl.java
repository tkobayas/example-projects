package org.jboss.errai.bus.client.framework;

import com.example.client.shared.Member;
import com.example.client.shared.MemberService;
import java.lang.annotation.Annotation;
import java.util.List;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.bus.client.api.base.MessageBuilder;

public class RpcProxyLoaderImpl implements RpcProxyLoader {
  public void loadProxies(final MessageBus bus) {
    class MemberServiceImpl implements MemberService, RPCStub {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private Annotation[] qualifiers;
      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setQualifiers(Annotation[] quals) {
        qualifiers = quals;
      }

      public void register(Member a0) {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.example.client.shared.MemberService").endpoint("register:com.example.client.shared.Member:", qualifiers, new Object[] { a0 }).respondTo(void.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.example.client.shared.MemberService").endpoint("register:com.example.client.shared.Member:", qualifiers, new Object[] { a0 }).respondTo(void.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
      }

      public List retrieveAllMembersOrderedByName() {
        if (errorCallback == null) {
          MessageBuilder.createCall().call("com.example.client.shared.MemberService").endpoint("retrieveAllMembersOrderedByName:", qualifiers, new Object[] { }).respondTo(List.class, remoteCallback).defaultErrorHandling().sendNowWith(bus);
        } else {
          MessageBuilder.createCall().call("com.example.client.shared.MemberService").endpoint("retrieveAllMembersOrderedByName:", qualifiers, new Object[] { }).respondTo(List.class, remoteCallback).errorsHandledBy(errorCallback).sendNowWith(bus);
        }
        return null;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(MemberService.class, new ProxyProvider() {
      public Object getProxy() {
        return new MemberServiceImpl();
      }
    });
  }
}