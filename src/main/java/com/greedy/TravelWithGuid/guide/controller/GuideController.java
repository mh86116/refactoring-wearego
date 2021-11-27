package com.greedy.TravelWithGuid.guide.controller;

import com.greedy.TravelWithGuid.guide.service.GuideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;
}
