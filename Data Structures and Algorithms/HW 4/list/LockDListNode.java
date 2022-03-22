class LockDListNode extends DListNode{
  public Object item;
  protected DListNode prev;
  protected DListNode next;
  protected boolean locked;
  LockDListNode(Object i, DListNode p, DListNode n){
    super(i,p,n);
    lock
  }
  public void lockNode(DListNode node){
      if(node!=null) {
        node= (LockDListNode) node;
        node.locked=true;
      }
  }
}