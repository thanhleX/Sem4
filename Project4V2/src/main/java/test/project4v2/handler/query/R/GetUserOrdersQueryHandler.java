package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.UserOrderDTO;
import test.project4v2.handler.QueryHandler;
import test.project4v2.query.GetUserOrdersQuery;
import test.project4v2.repository.UserOderRepository;

@Component
public class GetUserOrdersQueryHandler implements QueryHandler<GetUserOrdersQuery, UserOrderDTO> {

    @Autowired
    private UserOderRepository userOderRepository;
    @Override
    public UserOrderDTO getHandle(GetUserOrdersQuery query) {
        return userOderRepository.findById(query.getUserId()).orElse(null);
    }
}
