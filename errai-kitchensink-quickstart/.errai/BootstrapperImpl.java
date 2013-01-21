package org.jboss.errai.ioc.client;

import com.example.client.local.KitchenSinkApp;
import com.example.client.shared.Member;
import com.example.client.shared.MemberService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import java.lang.annotation.Annotation;
import java.util.Set;
import javax.inject.Provider;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.framework.Subscription;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.enterprise.client.cdi.AbstractCDIEventCallback;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.CDIProtocol;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.api.qualifiers.Any;
import org.jboss.errai.ioc.client.api.qualifiers.BuiltInQualifiers;
import org.jboss.errai.ioc.client.container.BeanRef;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

public class BootstrapperImpl implements Bootstrapper {
  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void com_example_client_local_KitchenSinkApp_memberService(KitchenSinkApp instance, Caller value) /*-{
    instance.@com.example.client.local.KitchenSinkApp::memberService = value;
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    CDIEventTypeLookup.get().addLookup("com.example.client.shared.Member", "java.io.Serializable");
    CDIEventTypeLookup.get().addLookup("com.example.client.shared.Member", "java.lang.Comparable");
    CDIEventTypeLookup.get().addLookup("com.example.client.shared.Member", "java.lang.Object");
    new CDI().__resetSubsystem();
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
    CreationalContext context = injContext.getRootContext();
    final CreationalCallback<CallerProvider> inj1754_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
      public CallerProvider getInstance(final CreationalContext context) {
        Class beanType = CallerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final CallerProvider inj1743_CallerProvider = new CallerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1743_CallerProvider);
        return inj1743_CallerProvider;
      }
    };
    final CallerProvider inj1743_CallerProvider = inj1754_CallerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RequestDispatcherProvider> inj1755_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
      public RequestDispatcherProvider getInstance(final CreationalContext context) {
        Class beanType = RequestDispatcherProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RequestDispatcherProvider inj1751_RequestDispatcherProvider = new RequestDispatcherProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1751_RequestDispatcherProvider);
        return inj1751_RequestDispatcherProvider;
      }
    };
    final RequestDispatcherProvider inj1751_RequestDispatcherProvider = inj1755_RequestDispatcherProvider_creationalCallback.getInstance(context);
    final CreationalCallback<SenderProvider> inj1756_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
      public SenderProvider getInstance(final CreationalContext context) {
        Class beanType = SenderProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final SenderProvider inj1741_SenderProvider = new SenderProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1741_SenderProvider);
        return inj1741_SenderProvider;
      }
    };
    final SenderProvider inj1741_SenderProvider = inj1756_SenderProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RootPanelProvider> inj1757_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
      public RootPanelProvider getInstance(final CreationalContext context) {
        Class beanType = RootPanelProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RootPanelProvider inj1753_RootPanelProvider = new RootPanelProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1753_RootPanelProvider);
        return inj1753_RootPanelProvider;
      }
    };
    final RootPanelProvider inj1753_RootPanelProvider = inj1757_RootPanelProvider_creationalCallback.getInstance(context);
    final CreationalCallback<IOCBeanManagerProvider> inj1758_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
      public IOCBeanManagerProvider getInstance(final CreationalContext context) {
        Class beanType = IOCBeanManagerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final IOCBeanManagerProvider inj1745_IOCBeanManagerProvider = new IOCBeanManagerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1745_IOCBeanManagerProvider);
        return inj1745_IOCBeanManagerProvider;
      }
    };
    final IOCBeanManagerProvider inj1745_IOCBeanManagerProvider = inj1758_IOCBeanManagerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<MessageBusProvider> inj1759_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
      public MessageBusProvider getInstance(final CreationalContext context) {
        Class beanType = MessageBusProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessageBusProvider inj1737_MessageBusProvider = new MessageBusProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1737_MessageBusProvider);
        return inj1737_MessageBusProvider;
      }
    };
    final MessageBusProvider inj1737_MessageBusProvider = inj1759_MessageBusProvider_creationalCallback.getInstance(context);
    final CreationalCallback<KitchenSinkApp> inj1760_KitchenSinkApp_creationalCallback = new CreationalCallback<KitchenSinkApp>() {
      public KitchenSinkApp getInstance(final CreationalContext context) {
        Class beanType = KitchenSinkApp.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final KitchenSinkApp inj1685_KitchenSinkApp = new KitchenSinkApp();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1685_KitchenSinkApp);
        com_example_client_local_KitchenSinkApp_memberService(inj1685_KitchenSinkApp, inj1743_CallerProvider.provide(new Class[] { MemberService.class }, null));
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj1685_KitchenSinkApp.createUI();
              }
            });
          }
        });
        final Subscription var1 = CDI.subscribe("com.example.client.shared.Member", new AbstractCDIEventCallback() {
          {
            qualifierSet.add("com.example.client.shared.New");
          }
          public void callback(final Message message) {
            Set<String> msgQualifiers = message.get(Set.class, CDIProtocol.Qualifiers);
            if (qualifierSet.equals(msgQualifiers) || ((msgQualifiers == null) && qualifierSet.isEmpty())) {
              GWT.runAsync(new RunAsyncCallback() {
                public void onFailure(Throwable throwable) {
                  throw new RuntimeException("failed to run asynchronously", throwable);
                }
                public void onSuccess() {
                  inj1685_KitchenSinkApp.onMemberAdded(message.get(Member.class, CDIProtocol.BeanReference));
                }
              });
            }
          }
          public String toString() {
            return "Observer: com.example.client.shared.Member [@com.example.client.shared.New()]";
          }
        });
        final Subscription var2 = inj1737_MessageBusProvider.get().subscribe("cdi.event:com.example.client.shared.Member", CDI.ROUTING_CALLBACK);
        context.addDestructionCallback(inj1685_KitchenSinkApp, new DestructionCallback<KitchenSinkApp>() {
          public void destroy(final KitchenSinkApp obj) {
            var1.remove();
            var2.remove();
          }
        });
        return inj1685_KitchenSinkApp;
      }
    };
    final KitchenSinkApp inj1685_KitchenSinkApp = inj1760_KitchenSinkApp_creationalCallback.getInstance(context);
    final CreationalCallback<InstanceProvider> inj1761_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
      public InstanceProvider getInstance(final CreationalContext context) {
        Class beanType = InstanceProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InstanceProvider inj1749_InstanceProvider = new InstanceProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1749_InstanceProvider);
        return inj1749_InstanceProvider;
      }
    };
    final InstanceProvider inj1749_InstanceProvider = inj1761_InstanceProvider_creationalCallback.getInstance(context);
    final CreationalCallback<EventProvider> inj1762_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
      public EventProvider getInstance(final CreationalContext context) {
        Class beanType = EventProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final EventProvider inj1739_EventProvider = new EventProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1739_EventProvider);
        return inj1739_EventProvider;
      }
    };
    final EventProvider inj1739_EventProvider = inj1762_EventProvider_creationalCallback.getInstance(context);
    final CreationalCallback<DisposerProvider> inj1763_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
      public DisposerProvider getInstance(final CreationalContext context) {
        Class beanType = DisposerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DisposerProvider inj1747_DisposerProvider = new DisposerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1747_DisposerProvider);
        org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1747_DisposerProvider, inj1745_IOCBeanManagerProvider.get());
        return inj1747_DisposerProvider;
      }
    };
    final DisposerProvider inj1747_DisposerProvider = inj1763_DisposerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<InitBallotProvider> inj1764_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
      public InitBallotProvider getInstance(final CreationalContext context) {
        Class beanType = InitBallotProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InitBallotProvider inj1735_InitBallotProvider = new InitBallotProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1735_InitBallotProvider);
        return inj1735_InitBallotProvider;
      }
    };
    final InitBallotProvider inj1735_InitBallotProvider = inj1764_InitBallotProvider_creationalCallback.getInstance(context);
    injContext.addBean(CallerProvider.class, inj1754_CallerProvider_creationalCallback, inj1743_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj1754_CallerProvider_creationalCallback, inj1743_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RequestDispatcherProvider.class, inj1755_RequestDispatcherProvider_creationalCallback, inj1751_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj1755_RequestDispatcherProvider_creationalCallback, inj1751_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(SenderProvider.class, inj1756_SenderProvider_creationalCallback, inj1741_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj1756_SenderProvider_creationalCallback, inj1741_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RootPanelProvider.class, inj1757_RootPanelProvider_creationalCallback, inj1753_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj1757_RootPanelProvider_creationalCallback, inj1753_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IOCBeanManagerProvider.class, inj1758_IOCBeanManagerProvider_creationalCallback, inj1745_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj1758_IOCBeanManagerProvider_creationalCallback, inj1745_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessageBusProvider.class, inj1759_MessageBusProvider_creationalCallback, inj1737_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj1759_MessageBusProvider_creationalCallback, inj1737_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(KitchenSinkApp.class, inj1760_KitchenSinkApp_creationalCallback, inj1685_KitchenSinkApp, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InstanceProvider.class, inj1761_InstanceProvider_creationalCallback, inj1749_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj1761_InstanceProvider_creationalCallback, inj1749_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventProvider.class, inj1762_EventProvider_creationalCallback, inj1739_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj1762_EventProvider_creationalCallback, inj1739_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DisposerProvider.class, inj1763_DisposerProvider_creationalCallback, inj1747_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj1763_DisposerProvider_creationalCallback, inj1747_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InitBallotProvider.class, inj1764_InitBallotProvider_creationalCallback, inj1735_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj1764_InitBallotProvider_creationalCallback, inj1735_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    return injContext;
  }
}