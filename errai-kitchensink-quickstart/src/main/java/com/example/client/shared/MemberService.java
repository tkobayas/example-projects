package com.example.client.shared;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 * Errai RPC interface that specifies which methods the client can invoke on the
 * server-side service.
 *
 * @author Jonathan Fuerth <jfuerth@redhat.com>
 * @author Christian Sadilek <csadilek@redhat.com>
 * @see com.example.controller.MemberServiceImpl
 */
@Remote
public interface MemberService {
  public void register(Member member);
  public List<Member> retrieveAllMembersOrderedByName();
}
