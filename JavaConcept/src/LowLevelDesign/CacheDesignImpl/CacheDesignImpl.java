package LowLevelDesign.CacheDesignImpl;

public class CacheDesignImpl {
    public static void main(String[] args) {
        int size = 2;
      CacheService<String,Integer> cacheService = CacheFactory.getCache(3);
        cacheService.put("test",1200);
        cacheService.put("Media",19011);
        cacheService.put("Himanshu",1457);
        cacheService.put("cdd",14111);
        cacheService.put("Himanshu",1413411);
        cacheService.put("sristi",555555);
        cacheService.put("sristi",444444);
        cacheService.put("geeta",33333);
        cacheService.put("Amit",22222);
        cacheService.put("aditya",11111);
      cacheService.put("Amit",99999);
      cacheService.put("aditya",343434345);


        System.out.println(""+cacheService.get("test"));
        System.out.println(""+cacheService.get("Himanshu"));
        System.out.println(""+cacheService.get("Media"));
        System.out.println(""+cacheService.get("cdd"));

        System.out.println(""+cacheService.get("sristi"));
        System.out.println(""+cacheService.get("geeta"));
        System.out.println(""+cacheService.get("Amit"));
        System.out.println(""+cacheService.get("aditya"));

    }
}

/*
Capacity=3
To Store Key: test Value: 1200
Stored: test=1200
printing cache after put operation
Doubly Link List: test --------------------
-------------------------------
To Store Key: Media Value: 19011
Stored: Media=19011
printing cache after put operation
Doubly Link List: test Media --------------------
-------------------------------
To Store Key: Himanshu Value: 1457
Stored: Himanshu=1457
printing cache after put operation
Doubly Link List: test Media Himanshu --------------------
-------------------------------
To Store Key: cdd Value: 14111
Storage is full
Doubly Link List: test Media Himanshu --------------------
Evicted Node{data=test}
Doubly Link List: Media Himanshu --------------------
Eviction policy saying to remove this key from storage =test
To Store Key: cdd Value: 14111
Stored: cdd=14111
printing cache after put operation
Doubly Link List: Media Himanshu cdd --------------------
-------------------------------
To Store Key: Himanshu Value: 1413411
key already exists
To Store Key: Himanshu Value: 1413411
Stored: Himanshu=1413411
printing cache after put operation
Doubly Link List: Media cdd Himanshu --------------------
-------------------------------
To Store Key: sristi Value: 555555
Storage is full
Doubly Link List: Media cdd Himanshu --------------------
Evicted Node{data=Media}
Doubly Link List: cdd Himanshu --------------------
Eviction policy saying to remove this key from storage =Media
To Store Key: sristi Value: 555555
Stored: sristi=555555
printing cache after put operation
Doubly Link List: cdd Himanshu sristi --------------------
-------------------------------
To Store Key: sristi Value: 444444
key already exists
To Store Key: sristi Value: 444444
Stored: sristi=444444
printing cache after put operation
Doubly Link List: cdd Himanshu sristi --------------------
-------------------------------
To Store Key: geeta Value: 33333
Storage is full
Doubly Link List: cdd Himanshu sristi --------------------
Evicted Node{data=cdd}
Doubly Link List: Himanshu sristi --------------------
Eviction policy saying to remove this key from storage =cdd
To Store Key: geeta Value: 33333
Stored: geeta=33333
printing cache after put operation
Doubly Link List: Himanshu sristi geeta --------------------
-------------------------------
To Store Key: Amit Value: 22222
Storage is full
Doubly Link List: Himanshu sristi geeta --------------------
Evicted Node{data=Himanshu}
Doubly Link List: sristi geeta --------------------
Eviction policy saying to remove this key from storage =Himanshu
To Store Key: Amit Value: 22222
Stored: Amit=22222
printing cache after put operation
Doubly Link List: sristi geeta Amit --------------------
-------------------------------
To Store Key: aditya Value: 11111
Storage is full
Doubly Link List: sristi geeta Amit --------------------
Evicted Node{data=sristi}
Doubly Link List: geeta Amit --------------------
Eviction policy saying to remove this key from storage =sristi
To Store Key: aditya Value: 11111
Stored: aditya=11111
printing cache after put operation
Doubly Link List: geeta Amit aditya --------------------
-------------------------------
To Store Key: Amit Value: 99999
key already exists
To Store Key: Amit Value: 99999
Stored: Amit=99999
printing cache after put operation
Doubly Link List: geeta aditya Amit --------------------
-------------------------------
To Store Key: aditya Value: 343434345
key already exists
To Store Key: aditya Value: 343434345
Stored: aditya=343434345
printing cache after put operation
Doubly Link List: geeta Amit aditya --------------------
-------------------------------
Key not found
null
Key not found
null
Key not found
null
Key not found
null
Key not found
null
Retrieved: geeta=33333
Doubly Link List: Amit aditya geeta --------------------
-------------------------------
33333
Retrieved: Amit=99999
Doubly Link List: aditya geeta Amit --------------------
-------------------------------
99999
Retrieved: aditya=343434345
Doubly Link List: geeta Amit aditya --------------------
-------------------------------
343434345
 */