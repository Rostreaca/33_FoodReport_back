package com.kh.foodreport.domain.notice.model.service;

import com.kh.foodreport.domain.notice.model.dto.NoticeResponse;

public interface NoticeService {

	NoticeResponse findAllNotices(int page);

}
