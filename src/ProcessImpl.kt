package mutex

/**
 * Distributed mutual exclusion implementation.
 * All functions are called from the single main thread.
 *
 * @author <First-Name> <Last-Name> // todo: replace with your name
 */
class ProcessImpl(private val env: Environment) : Process {
    override fun onMessage(srcId: Int, message: Message) {
        /* todo: write implementation here */
    }

    override fun onLockRequest() {
        /* todo: write implementation here */
        env.locked() // call only when critical section can be entered
    }

    override fun onUnlockRequest() {
        /* todo: write implementation here */
        env.unlocked()
    }
}
