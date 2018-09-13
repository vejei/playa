package io.github.zeleven.playa.data.model;

import java.util.List;

public class ArticleListResponse {
	private int curPage;
	private List<Article> datas;
	private int offset;
	private boolean over;
	private int pageCount;
	private int size;
	private int total;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<Article> getDatas() {
		return datas;
	}

	public void setDatas(List<Article> datas) {
		this.datas = datas;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}