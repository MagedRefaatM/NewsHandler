package com.newshandler.model;

//@Database(entities = { FeedItem.class }, version = 1)
//public abstract class RSSFeedDatabase  extends RoomDatabase {
//
//    public abstract NewsDao getNewsDao();
//
//    private static RSSFeedDatabase instance;
//
//    public static synchronized RSSFeedDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    RSSFeedDatabase.class, "news_database")
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//    }
//}