
public class Gamer {
    private int score = 0;
    private char color;

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public int subMove(Field field, int sign, int corX, int corY, boolean change) {
        int cnt = 0;
        for (int i = corX + sign; i < 8 && i >= 0; i += sign) { //y+
            if (field.getColor(i, corY) == this.getColor() && i != corX + sign) {
                for (int j = i; ; j -= sign) {
                    if ((sign > 0 && j < corX) || (sign < 0 && j > corX)) {
                        break;
                    }
                    cnt++;
                    if (change) {
                        field.changeColor(this.color, j, corY);
                    }
                }
            } else if (field.getColor(i, corY) == '.' || field.getColor(i, corY) == this.color) {
                break;
            }
        }
        for (int i = corY + sign; i < 8 && i >= 0; i += sign) { //x-
            if (field.getColor(corX, i) == this.getColor() && i != corY + sign) {
                for (int j = i; ; j -= sign) {
                    if ((sign > 0 && j < corY) || (sign < 0 && j > corY)) {
                        break;
                    }
                    cnt++;
                    if (change) {
                        field.changeColor(this.color, corX, j);
                    }
                }
            } else if (field.getColor(corX, i) == '.' || field.getColor(corX, i) == this.color) {
                break;
            }
        }
        for (int i = corX + sign, j = corY + sign; i < 8 && i >= 0 && j < 8 && j >= 0; i += sign, j += sign) { //x-
            if (field.getColor(i, j) == this.getColor() && j != corY + sign) {
                for (int k = i, l = j; ; k -= sign, l -= sign) {
                    if ((sign > 0 && k < corX && l < corY) || (sign < 0 && k > corX && l > corY)) {
                        break;
                    }
                    cnt++;
                    if (change) {
                        field.changeColor(this.color, k, l);
                    }
                }
            } else if (field.getColor(i, j) == '.' || field.getColor(i, j) == this.color) {
                break;
            }
        }
        for (int i = corX - sign, j = corY + sign; i < 8 && i >= 0 && j < 8 && j >= 0; i -= sign, j += sign) { //x-
            if (field.getColor(i, j) == this.getColor() && j != corY + sign) {
                for (int k = i, l = j; ; k += sign, l -= sign) {
                    if ((sign > 0 && k > corX && l < corY) || (sign < 0 && k < corX && l > corY)) {
                        break;
                    }
                    cnt++;
                    if (change) {
                        field.changeColor(this.color, k, l);
                    }
                }
            } else if (field.getColor(i, j) == '.' || field.getColor(i, j) == this.color) {
                break;
            }
        }
        return cnt;
    }

    public int move(Field field, int corX, int corY) {
        int cnt = 0;
        if (field.getColor(corX, corY) != '.') {
            return cnt;
        }
        cnt += subMove(field, 1, corX, corY, true);
        cnt += subMove(field, -1, corX, corY, true);
        if (cnt != 0) {
            field.turn = !field.turn;
        }
        return cnt;
    }
}
