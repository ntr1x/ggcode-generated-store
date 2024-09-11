WITH attachments (uri, size, page_count, name, type_id) AS (
  VALUES
    ('https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf', 88226, 4, 'Instruction', 2)
  , ('https://pdfobject.com/pdf/sample.pdf', 18810, 1, 'Certificate', 3)
)
INSERT INTO "public"."product_attachment" (id, uri, size, page_count, name, type_id, product_id)
SELECT
  gen_random_uuid(),
  a.uri,
  a.size,
  a.page_count,
  a.name,
  a.type_id,
  p.id
FROM "public"."product" p
CROSS JOIN attachments a
