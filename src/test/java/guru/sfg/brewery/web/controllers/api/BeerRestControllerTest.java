package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.web.controllers.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class BeerRestControllerTest extends BaseIT {

    @Test
    void findBeers_whenGetRequest() throws Exception{
        mockMvc.perform(get("/api/v1/beer/"))
                .andExpect(status().isOk());
    }

    @Test
    void findBeersById_whenUUID() throws Exception{
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void findBeersByUPC() throws Exception{
        mockMvc.perform(get("/api/v1/beerUpc/546554"))
                .andExpect(status().isOk());
    }

}