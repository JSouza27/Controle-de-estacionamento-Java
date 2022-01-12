package challenge;

import java.util.ArrayList;

public class Estacionamento {

  ArrayList<Carro> parkingSpace = new ArrayList<>();
  int parkingLimit = 10;

  public void estacionar(Carro carro) {
    int parkedCars = carrosEstacionados();

    if (validateCarro(carro)) {
      throw new EstacionamentoException("É obrigatório informar o motorista.");
    }

    if (validateMotorista(carro.getMotorista())) {
      throw new EstacionamentoException("O motorista deve ser maior de 18 anos.");
    }

    if (validateMotorista(carro.getMotorista())) {
      throw new EstacionamentoException("A habilitação não deve estar suspensa.");
    }

    if (parkedCars == parkingLimit) {
      if (checkSenirioridade()) {
        for (Carro car: parkingSpace) {
          if (car.getMotorista().getIdade() <= 55){
            parkingSpace.remove(car);
            parkingSpace.add(carro);
            return;
          }
        }
        throw new EstacionamentoException("Estacionamento cheio, volte mais tarde.");
      } else {
        parkingSpace.remove(0);
      }
    }

    parkingSpace.add(carro);

//    if (parkedCars == parkingLimit) {
//      if (parkingSpace.get(0).getMotorista().getIdade() > ageForException) {
//        int index = parkingSpace.indexOf(carro.getMotorista().getIdade() < 55);
//
//        if (index == -1) {
//          throw new EstacionamentoException("Estacionamento cheio, volte mais tarde.");
//        } else {
//          parkingSpace.remove(index);
//          parkingSpace.add(carro);
//        }
//      }
//      parkingSpace.remove(0);
//      parkingSpace.add(carro);
//    }
  }

  public int carrosEstacionados() {
    return parkingSpace.size();
  }

  public boolean carroEstacionado(Carro carro) {
    boolean isParked = parkingSpace.contains(carro);
    return isParked;
  }

  private boolean validateMotorista(Motorista motorista) {
    if (motorista.getIdade() < 18) return true;
    if (motorista.getPontos() > 20) return true;

    return false;
  }

  private boolean validateCarro(Carro carro) {
    return carro.getMotorista() == null;
  }

  private boolean checkSenirioridade() {
    return parkingSpace.get(0).getMotorista().getIdade() > 55;
  }
}
