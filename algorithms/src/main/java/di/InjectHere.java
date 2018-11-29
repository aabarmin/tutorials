package di;

import javax.inject.Inject;

/**
 * @TODO write a discription
 */
public class InjectHere {
    @Inject
    @OtherImpl
    private SomeService service;
}
