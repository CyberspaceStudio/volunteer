package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.InvitationCode;

public interface InvitationCodeService {
    String insertInvitationCode(InvitationCode invitationCode);

    InvitationCode checkInvitationCode(String code);

    String generateInvitationCode();
}
