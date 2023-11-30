public class Reply extends Action {
    static int id2 = 0;

    Reply(String msg) {
        this.content = msg;
        id2++;
        this.id = id2;
    }
}
