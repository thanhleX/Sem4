package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.UserOrderDTO;
import test.project4v2.handler.QueryHandler;
import test.project4v2.repository.UserOrderRepository;


import java.util.List;

@Component
public class GetUserOrdersQuery implements QueryHandler<test.project4v2.query.GetUserOrdersQuery, List<UserOrderDTO>> {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public List<UserOrderDTO> getHandle(test.project4v2.query.GetUserOrdersQuery query) {
         return userOrderRepository.findByUserId(query.getUserId());
    }
}