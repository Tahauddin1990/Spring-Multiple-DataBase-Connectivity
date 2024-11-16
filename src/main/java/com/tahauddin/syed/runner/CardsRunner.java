package com.tahauddin.syed.runner;

import com.tahauddin.syed.domain.udemy.repo.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class CardsRunner implements CommandLineRunner {

    private final CardsRepository cardsRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
