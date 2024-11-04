package test.project4v2.query;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeLongArrayAccess;
import lombok.Getter;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.command.C.CreateUserCommand;
import test.project4v2.dto.UserOrderDTO;
import test.project4v2.entity.User;

import java.util.List;

@Getter
@Setter
public class GetUserOrdersQuery  implements Mediator.Query<List<UserOrderDTO>>{
    private User userId;
    public GetUserOrdersQuery(User userId) {
        this.userId = userId;
    }
}
