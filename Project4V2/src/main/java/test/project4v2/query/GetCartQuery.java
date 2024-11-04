package test.project4v2.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.entity.User;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCartQuery implements test.project4v2.Mediator.Mediator.Query<test.project4v2.dto.CartDTO> {
    private User userId;
}
