--clone project về máy
git clone https://github.com/ngongiotieudao/PRJ321_Bun24

--để upload 1 project lên github:
b1 tạo new repository trên github > copy link về.

b2:
mở git gui hoặc terminal

git init

git add . //để add tất cả file lên git

git status 

git commit -m "nội dung" //note lại những thay đổi lên github

git branch -M main //main là branch. trong dự án có thể có nhiều branch. khi cần thì mới merge vào main

git remote add origin <link_repo>

git push -u origin main //nếu không được thì git push -fetch -u origin main //push code lên github ở nhánh(branch) main


--để lấy code mới cập nhật về local
git pull origin main 

--để đẩy code mới lên github
làm từ bước git add .
