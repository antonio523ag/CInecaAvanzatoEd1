package dev.antoniogrillo.primoprogettospring.service.impl;

import dev.antoniogrillo.primoprogettospring.repository.IndirizzoRepository;
import dev.antoniogrillo.primoprogettospring.service.def.IndirizzoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndirizzoServiceImpl implements IndirizzoService {

    private final IndirizzoRepository repo;
}
