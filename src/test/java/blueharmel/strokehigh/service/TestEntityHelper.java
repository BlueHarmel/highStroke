package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.DeletedTimeEntity;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

@TestComponent
public class TestEntityHelper {
    public static void setDeletedAtForTest(DeletedTimeEntity entity, LocalDateTime deletedAt) {
        ReflectionTestUtils.setField(entity, "deletedAt", deletedAt);
    }
}

