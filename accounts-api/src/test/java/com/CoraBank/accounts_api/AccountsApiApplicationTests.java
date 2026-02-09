package com.corabank.accounts_api;

import com.corabank.accounts_api.controller.AccountController;
import com.corabank.accounts_api.model.Account;
import com.corabank.accounts_api.model.dto.CreateAccountRequest;
import com.corabank.accounts_api.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAccount() throws Exception {
        Account account = Account.builder()
                .name("João")
                .cpf("12345678901")
                .build();
        account.setId(1L);

        Mockito.doReturn(account)
                .when(accountService)
                .create("João", "12345678901", null);


        CreateAccountRequest request =
                new CreateAccountRequest("João", "12345678901", null);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }
}

