package dev.antoniogrillo.primoprogettospring.service.def;

import dev.antoniogrillo.primoprogettospring.dto.internal.LoginResponse;
import dev.antoniogrillo.primoprogettospring.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;

public interface UtenteService {
    LoginResponse login(LoginRequestDTO request);
}
