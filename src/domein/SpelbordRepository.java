package domein;

import persistentie.SpelbordMapper;

	/**
	 * SpelbordRepository bevat een constructor en instantieert een SpelbordMapper
	 * 
	 * @author Gr86
	 */
public class SpelbordRepository {
	
	/**
	 * Default constructor van SpelbordRepository
	 * instantieert een nieuwe SpelbordMapper
	 */
	public SpelbordRepository() {
        new SpelbordMapper();
    }
}