package com.CoraBank.accounts_api;

import com.corabank.accounts_api.controller.AccountController;
import com.corabank.accounts_api.model.Account;
import com.corabank.accounts_api.model.dto.AccountResponse;
import com.corabank.accounts_api.model.dto.CreateAccountRequest;
import com.corabank.accounts_api.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AccountsApiApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void shouldCreateAccount() throws Exception {
        Account account = Account.builder().name("João").cpf("12345678901").build();
        account.setId(1L);
        Mockito.when(accountService.create(eq("João"), eq("12345678901"))).thenReturn(account);

        CreateAccountRequest request = new CreateAccountRequest("João", "12345678901");

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    void shouldListAccounts() throws Exception {
        AccountResponse response1 = AccountResponse.builder()
                .id(1L).name("João").cpf("12345678901").build();

        AccountResponse response2 = AccountResponse.builder()
                .id(2L).name("Maria").cpf("10987654321").build();

        Mockito.when(accountService.listAll()).thenReturn(List.of(response1, response2));

        mockMvc.perform(get("/accounts")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João"))
                .andExpect(jsonPath("$[1].name").value("Maria"));
    }


    @Test
    void shouldReturnBadRequestForInvalidCpf() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest("João", "123"); // CPF inválido

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
