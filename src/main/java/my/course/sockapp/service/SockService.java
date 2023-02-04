package my.course.sockapp.service;
import my.course.sockapp.dto.SockRequest;
import my.course.sockapp.exception.InSufficientSockQuantity;
import my.course.sockapp.exception.InvalidSockRequestException;
import my.course.sockapp.model.Colour;
import my.course.sockapp.model.Size;
import my.course.sockapp.model.Sock;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
@Service
public class SockService {
    private final Map<Sock, Integer> socks = new HashMap<>();

    public void addSock(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        if (socks.containsKey(sock)){
            socks.put(sock,socks.get(sock) + sockRequest.getQuantity());
        }
        else {
            socks.put(sock,sockRequest.getQuantity());
        }
    }

    public void issueSock(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }

    public void removeDefectiveSocks(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }


    public void decreaseSockQuantity(SockRequest sockRequest){
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        int sockQuantity = socks.getOrDefault(sock, 0);
        if (sockQuantity >= sockRequest.getQuantity()){
            socks.put(sock,sockQuantity - sockRequest.getQuantity());
        }
        else {
            throw new InSufficientSockQuantity("Носков нет");
        }
    }

    public int getSockQuantity(Colour colour, Size size, Integer cottonMin, Integer cottonMax){
    int total = 0;
    for (Map.Entry<Sock, Integer> entry : socks.entrySet()){

        if (colour != null && !entry.getKey().getColour().equals(colour)) {
            continue;
        }

        if (size != null && !entry.getKey().getSize().equals(size)) {
            continue;
        }

        if (cottonMin != null && entry.getKey().getCottonPercentage() < cottonMin) {
            continue;
        }

        if (cottonMax != null && entry.getKey().getCottonPercentage() > cottonMax) {
            continue;
        }

        total += entry.getValue();
    }
        return total;
    }


    private void validateRequest(SockRequest sockRequest) {
    if (sockRequest.getColour() == null || sockRequest.getSize() == null){
        throw new InvalidSockRequestException("Все поля должны быть заполнены");
            }
    if(sockRequest.getQuantity()<= 0){
        throw new InvalidSockRequestException("Количество должно быть больше 0");
            }
    if (sockRequest.getCottonPercent() < 0 || sockRequest.getCottonPercent() > 100){
        throw new InvalidSockRequestException("Процент хлопка должен быть между 0 и 100");
             }
    }


    private Sock mapToSock(SockRequest sockRequest){
        Sock sock = new Sock(sockRequest.getColour(),
                sockRequest.getSize(),
                sockRequest.getCottonPercent());
        return sock;
    }



}
