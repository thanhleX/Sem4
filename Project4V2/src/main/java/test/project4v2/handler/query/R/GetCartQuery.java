package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.CartDTO;
import test.project4v2.handler.QueryHandler;
import test.project4v2.service.ShoppingCartService;


@Component
public class GetCartQuery implements QueryHandler<test.project4v2.query.GetCartQuery, CartDTO> {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Override
    public CartDTO getHandle(test.project4v2.query.GetCartQuery query) {
        return shoppingCartService.getCart(String.valueOf(query.getUserId()));
    }
}
