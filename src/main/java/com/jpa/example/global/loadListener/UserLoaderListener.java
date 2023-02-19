package com.jpa.example.global.loadListener;

import com.jpa.example.domain.user.User;
import com.jpa.example.domain.user.repository.UserRepository;
import com.jpa.example.global.enums.accountType;
import com.jpa.example.global.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoaderListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {


  private final UserRepository userRepository;

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public void onApplicationEvent(final ApplicationStartedEvent event) {

    User user = User.builder()
      .nickname("공인중개사1")
      .accountType(accountType.Realtor)
      .accountId("Realtor1")
      .build();

    User user2 = User.builder()
        .nickname("임대인1")
        .accountType(accountType.Lessor)
        .accountId("Lessor1")
        .build();

    User user3 = User.builder()
        .nickname("임차인1")
        .accountType(accountType.Lessee)
        .accountId("Lessee1")
        .build();

    createUserAssertNull(user);
    createUserAssertNull(user2);
    createUserAssertNull(user3);
  }

  private void createUserAssertNull(final User user) {
    if (ObjectUtil.isEmpty(userRepository.findByAccountIdOrNickname(user.getAccountId(), user.getNickname()))) {
      userRepository.save(user);
    }
  }

}
