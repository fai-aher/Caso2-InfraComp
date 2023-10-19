package logic;

public class Reference {
    private int virtualPage;
    private int offset;

    public Reference(int virtualPage, int offset) {
        this.virtualPage = virtualPage;
        this.offset = offset;
    }

    public int getVirtualPage() {
        return virtualPage;
    }

    public void setVirtualPage(int virtualPage) {
        this.virtualPage = virtualPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Reference [virtualPage=" + virtualPage + ", offset=" + offset + "]";
    }
}
